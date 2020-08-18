package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author pi
 * @date 2020/8/18 10:23:50
 */
public class TacoPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
