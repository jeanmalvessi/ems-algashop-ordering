package com.algaworks.algashop.ordering.domain.exception;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST = "BirthDate must be a past date";
    public static final String VALIDATION_ERROR_FULLNAME_IS_NULL = "FullName cannot be null";
    public static final String VALIDATION_ERROR_FIRSTNAME_IS_BLANK = "First name cannot be blank";
    public static final String VALIDATION_ERROR_LASTTNAME_IS_BLANK = "Last name cannot be blank";
    public static final String VALIDATION_ERROR_DOCUMENT_IS_BLANK = "Document cannot be blank";
    public static final String VALIDATION_ERROR_PHONE_IS_BLANK = "Phone cannot be blank";
    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "Email is invalid";
    public static final String VALIDATION_ERROR_LOYALTYPOINTS_IS_INVALID = "Loyalty points cannot be less than zero";
    public static final String VALIDATION_ERROR_ADD_LOYALTYPOINTS_IS_INVALID = "Loyalty points to add should be greater than zero";
    public static final String VALIDATION_ERROR_MONEY_CANNOT_BE_NEGATIVE = "Money cannot be negative";
    public static final String VALIDATION_ERROR_QUANTITY_IS_LESS_THAN_ONE = "Quantity cannot be less than one";
    public static final String VALIDATION_ERROR_QUANTITY_IS_LESS_THAN_ZERO = "Quantity cannot be less than zero";

    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is archived and cannot be changed";

}
