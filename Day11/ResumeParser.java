public class ResumeParser {

    public static Map<String, String> parseDetails(String text) {
        Map<String, String> data = new LinkedHashMap<>();

        data.put("Name", extractName(text));
        data.put("Email", extract(text, "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+"));
        data.put("Phone", extract(text, "\\+?\\d[\\d -]{9,13}"));
        data.put("Skills", extractSkills(text));
        data.put("Education", extractEducation(text));

        return data;
    }

    private static String extract(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        return matcher.find() ? matcher.group() : "Not Found";
    }

    private static String extractName(String text) {
        String[] lines = text.split("\n");
        return lines.length > 0 ? lines[0].trim() : "Not Found";
    }

    private static String extractSkills(String text) {
        String[] skills = {
            "Java", "Spring", "Spring Boot", "SQL",
            "Microservices", "React", "HTML", "CSS", "JavaScript"
        };

        return Arrays.stream(skills)
                .filter(text::contains)
                .collect(Collectors.joining(", "));
    }

    private static String extractEducation(String text) {
        Pattern p = Pattern.compile("(B\\.Tech|M\\.Tech|Bachelor|Master|Degree).*");
        Matcher m = p.matcher(text);
        return m.find() ? m.group() : "Not Found";
    }
}

