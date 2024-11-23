package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models;

public enum SkillType {
    SOFT_SKILL("Soft_kill"),
    UNSPECIFIC("Unspecific"),
    TECHNICAL_SKILL("Technical_skill");

    private String value;

    SkillType(String s) {
        this.value = s;
    }
}
