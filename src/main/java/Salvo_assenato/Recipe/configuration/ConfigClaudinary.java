package Salvo_assenato.Recipe.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class ConfigClaudinary {

    @Bean
    public Cloudinary uploadImage(@Value("${CLOUDINARY_NAME}") String name,
                                  @Value("${CLOUDINARY_APIKEY}") String apikey,
                                  @Value("${CLOUDINARY_SECRET}") String secret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", apikey);
        config.put("api_secret", secret);
        return new Cloudinary(config);
    }
}
