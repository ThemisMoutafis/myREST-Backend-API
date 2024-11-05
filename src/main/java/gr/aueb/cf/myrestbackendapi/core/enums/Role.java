package gr.aueb.cf.myrestbackendapi.core.enums;

public enum Role {
    // only one super_admin will be made manually, and and manually inserted in the db
    // API will not provide a way to create more SUPER_ADMINS for security purposes
    // SUPER_ADMIN will have privileges other users will not, such as access to
    SUPER_ADMIN,
    USER
}
