package br.com.alura.forum.infra.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class QueryPattern {
    private static final Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?);", Pattern.UNICODE_CHARACTER_CLASS);

    public static Pattern getPattern() {
        return pattern;
    }

    public static Matcher getMatcher(String search) { return pattern.matcher(search + ";");}

}
