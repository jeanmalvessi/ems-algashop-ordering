package com.algaworks.algashop.ordering.domain.exception;

public class ErrorMessages {

    public static final String VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST = "BirthDate must be a past date";
    public static final String VALIDATION_ERROR_FULLNAME_IS_NULL = "FullName cannot be null";
    public static final String VALIDATION_ERROR_FULLNAME_IS_BLANK = "FullName cannot be blank";
    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "Email is invalid";
    public static final String VALIDATION_ERROR_LOYALTYPOINTS_IS_INVALID = "Loyalty points cannot be less than zero";
    public static final String VALIDATION_ERROR_ADD_LOYALTYPOINTS_IS_INVALID = "Loyalty points to add should be greater than zero";

    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is archived and cannot be changed";

}
