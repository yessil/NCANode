package kz.ncanode.dto.request;

import kz.ncanode.dto.certificate.CertificateRevocation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Jacksonized
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CmsVerifyRequest extends VerifyRequest {
    @NotEmpty
    private String cms;
    private String data;
    @NotNull
    private Boolean checkOcsp;
    @NotNull
    private Boolean checkCrl;
    public Set<CertificateRevocation> setRevocationCheck(){
        if (checkCrl && checkOcsp) {
            setRevocationCheck(Set.of(CertificateRevocation.CRL, CertificateRevocation.OCSP));
        } else if (checkOcsp){
            setRevocationCheck(Set.of(CertificateRevocation.OCSP));
        } else if (checkCrl){
            setRevocationCheck(Set.of(CertificateRevocation.CRL));
        }
        return super.getRevocationCheck();
    }
}
