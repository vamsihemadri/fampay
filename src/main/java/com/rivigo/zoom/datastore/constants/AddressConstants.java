package com.rivigo.zoom.datastore.constants;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AddressConstants {

  public static final List<String> SAM_USER_ROLES =
      ImmutableList.of(
          "ZOOM_CLM", "ZOOM_HQTR_CLM", "ZOOM_RM", "ZOOM_HQTR_RM", "ZOOM_HQTR", "ZOOM_HOD");

  public static final List<String> CAPTAIN_USER_ROLES =
      ImmutableList.of("ZOOM_CAPTAIN", "ZOOM_BF_CAPTAIN");

  public static final List<String> PA_SA_USER_ROLES =
      ImmutableList.of("ZOOM_PA", "ZOOM_SA_CASHIER", "ZOOM_BF_OA");

  public static final List<String> CLIENT_USER_ROLES =
      ImmutableList.of(
          "ROLE_ZOOM_CLIENT_ADMIN", "ROLE_ZOOM_CLIENT_CONSIGNOR", "ROLE_ZOOM_CLIENT_CONSIGNEE");

  @UtilityClass
  public static class EsQuery {

    public static final String ORG_ID_KEY = "organizationId";

    public static final String LAST_USED_AT_AS_CONSIGNOR_KEY = "lastUsedAtAsConsignor";
    public static final String LAST_USED_AT_AS_CONSIGNEE_KEY = "lastUsedAtAsConsignee";
    public static final String USAGE_FREQUENCY_CONSIGNOR_KEY = "consignorUsageFrequency";
    public static final String USAGE_FREQUENCY_CONSIGNEE_KEY = "consigneeUsageFrequency";

    public static final String JOIN_FIELD_KEY = "capm";
    public static final String IS_ACTIVE_KEY = "isActive";
    public static final String IS_ACTIVE_TRUE_VALUE = "true";
    public static final String PIN_CODE_KEY = "pincode";
    public static final String PIN_CODE_NGRAMS_KEY = "pincode.ngrams";

    public static final String SOURCE_KEY = "replacedAddressSource";
    public static final String SOURCE_EWB_VALUE = "EWB";
    public static final String SOURCE_SAM_VALUE = "SAM";
    public static final String SOURCE_CLIENT_VALUE = "CLIENT";
    public static final String SOURCE_OA_VALUE = "OA"; // Refers to RetainedAddressSource.OA

    public static final String CLIENT_CODE_KEY = "clientCode";
    public static final String CLIENT_CODE_SHINGLES_KEY = "clientCode.shingles";
    public static final String ADDRESS_TYPE_KEY = "addressType";
    public static final String COMPANY_NAME_SHINGLES_KEY = "companyName.shingles";
    public static final String COMPANY_NAME_KEY = "companyName";
    public static final String DELIVERY_TYPE_KEY = "deliveryType";
    public static final String DELIVERY_CLIENT_KEY = "deliveryClient";
    public static final String CONTACT_PERSON_SHINGLES_KEY = "contactPerson.shingles";
    public static final String CONTACT_PERSON_KEY = "contactPerson";
    public static final String TAG_KEY = "tag";
    public static final String ID_KEY = "id";
    public static final String CAPM_ID_PREFIX = "capm-";
    public static final String ADDRESS_CODE_KEY = "addressCode";
    public static final String POC_CODE_KEY = "pointOfContactCode";
    public static final String PHONE_NUMBER_KEY = "phoneNumber";
    public static final String PHONE_NUMBER_NGRAMS_KEY = "phoneNumber.ngrams";
    public static final String TAX_ID_KEY = "taxId";
    public static final String TAX_ID_TYPE_KEY = "taxIdType";
    public static final String CLIENT_ADDRESS_CODE_KEY = "clientAddressCode";
    public static final String CODE_KEY = "code";
    public static final String DETAILED_ADDRESS_KEY = "detailedAddress";
    public static final String DETAILED_ADDRESS_SHINGLES_KEY = "detailedAddress.shingles";
    public static final String DETAILED_ADDRESS_RAW_KEY = "detailedAddress.raw";
    public static final String DETAILED_ADDRESS_NEW_KEY = "detailedAddressNew";
    public static final String STATE_KEY = "state";
    public static final String LANDMARK_KEY = "landmark";
    public static final String ADDRESS_KEY = "address";
    public static final String LOCALITY_NAME_KEY = "localityName";
    public static final String CREATED_BY_ID_KEY = "createdById";

    public static final String PICKUP = "PICKUP";
    public static final String DROP = "DROP";
    public static final String PICKUP_AND_DROP_LOWERCASE = "pickupanddrop";

    public static final String NUMERIC_INPUT = "[0-9]{3,}";
    public static final Set<String> STR_FIELDS_LOW_BOOST_NAMES =
        ImmutableSet.of(COMPANY_NAME_KEY, CONTACT_PERSON_KEY, DETAILED_ADDRESS_KEY);

    public static final String[] STR_FIELDS_DEFAULT_BOOST_NAMES =
        ImmutableList.of(
                CLIENT_ADDRESS_CODE_KEY,
                COMPANY_NAME_SHINGLES_KEY,
                COMPANY_NAME_KEY,
                DELIVERY_TYPE_KEY,
                DELIVERY_CLIENT_KEY,
                TAX_ID_KEY,
                TAG_KEY,
                POC_CODE_KEY,
                CONTACT_PERSON_SHINGLES_KEY,
                CONTACT_PERSON_KEY,
                DETAILED_ADDRESS_SHINGLES_KEY,
                DETAILED_ADDRESS_KEY,
                LANDMARK_KEY,
                LOCALITY_NAME_KEY)
            .toArray(new String[14]);

    public static final String[] NUM_FIELDS_NAMES =
        ImmutableList.of(
                PHONE_NUMBER_KEY, PHONE_NUMBER_NGRAMS_KEY, PIN_CODE_KEY, PIN_CODE_NGRAMS_KEY)
            .toArray(new String[4]);
  }

  public static final String ODA_FORMAT = "ODA_%s_%s";
  public static final String OPA_FORMAT = "OPA_%s_%s";
  public static final String SERVICEABLE = "SERVICEABLE";
  public static final Integer MAX_NEW_DELIVERY_ODA_CHARGE_FACTOR = 4;
  public static final Integer MAX_NEW_DELIVERY_ODA_TAT_FACTOR = 15;
  public static final Integer MAX_OLD_DELIVERY_ODA_CHARGE_FACTOR = 7;
  public static final Integer MAX_OLD_DELIVERY_ODA_TAT_FACTOR = 15;
  public static final Integer MAX_PICKUP_ODA_CHARGE_FACTOR = 4;
  public static final Integer MAX_PICKUP_ODA_TAT_FACTOR = 15;

  public static final String SERVICEABILITY_REQUIRED_FIELDS =
      "Pincode, User type, User code, Additional new charge for delivery, Additional new TAT for delivery, Additional charge for pickup, Additional TAT for pickup, is active";

  public static final int SERVICEABILITY_FIELDS_LENGTH = 10;
  public static final String DEFAULT = "default";

  public static final String NEW_ODA_DETAILS_MESSAGE_STRING = "New Oda Details";
  public static final String OLD_ODA_DETAILS_MESSAGE_STRING = "Old Oda Details";
  public static final String CUSTOM_CLIENT_MESSAGE_STRING = "Custom Client";
  public static final String DEFAULT_VENDOR_MESSAGE_STRING = "Default Vendor (Internal)";
}
