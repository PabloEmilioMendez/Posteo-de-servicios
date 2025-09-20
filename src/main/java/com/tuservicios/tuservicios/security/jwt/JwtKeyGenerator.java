import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // Genera una clave segura de 256 bits para el algoritmo HS256
        String secureKey = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
        System.out.println("Copia esta clave segura y pégala en tu application.properties:");
        System.out.println(secureKey);
    }
}