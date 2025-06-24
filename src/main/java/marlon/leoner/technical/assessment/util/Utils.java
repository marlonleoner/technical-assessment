package marlon.leoner.technical.assessment.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.Normalizer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static String generateSlug(String value) {
        String slug = value.toLowerCase();
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        slug = slug.replaceAll("[\\s\\p{Punct}]+", "-");
        slug = slug.replaceAll("-{2,}", "-").replaceAll("^-|-$", "");

        return slug;
    }
}
