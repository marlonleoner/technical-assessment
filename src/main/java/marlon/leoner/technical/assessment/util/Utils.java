package marlon.leoner.technical.assessment.util;

import java.text.Normalizer;

public class Utils {

    public static String generateSlug(String value) {
        String slug = value.toLowerCase();
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        slug = slug.replaceAll("[\\s\\p{Punct}]+", "-");
        slug = slug.replaceAll("-{2,}", "-").replaceAll("^-|-$", "");

        return slug;
    }
}
