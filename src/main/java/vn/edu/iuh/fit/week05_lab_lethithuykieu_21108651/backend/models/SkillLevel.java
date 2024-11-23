package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models;

public enum SkillLevel {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    MASTER("Master"),
    PROFESSIONAL("Professional");

    private String value;

    SkillLevel(String s) {
        this.value = s;
    }
}
