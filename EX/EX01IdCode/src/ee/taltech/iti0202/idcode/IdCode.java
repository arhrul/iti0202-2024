package ee.taltech.iti0202.iti0202.idcode;

import java.util.List;

public class IdCode {
    public static final int ID_CODE_LENGTH = 11;
    public static final int TARTU_FROM_1 = 11;
    public static final int TARTU_TO_1 = 20;
    public static final int TARTU_FROM_2 = 271;
    public static final int TARTU_TO_2 = 370;
    public static final int TALLINN_FROM_1 = 21;
    public static final int TALLINN_TO_1 = 220;
    public static final int TALLINN_FROM_2 = 471;
    public static final int TALLINN_TO_2 = 490;
    public static final int KOHTLA_FROM = 221;
    public static final int KOHTLA_TO = 270;
    public static final int NARVA_FROM = 371;
    public static final int NARVA_TO = 420;
    public static final int PARNU_FROM = 421;
    public static final int PARNU_TO = 470;
    public static final int PAIDE_FROM = 491;
    public static final int PAIDE_TO = 520;
    public static final int RAKVERE_FROM = 521;
    public static final int RAKVERE_TO = 570;
    public static final int VALGA_FROM = 571;
    public static final int VALGA_TO = 600;
    public static final int VILJANDI_FROM = 601;
    public static final int VILJANDI_TO = 650;
    public static final int VORU_FROM = 651;
    public static final int VORU_TO = 710;
    public static final int MAX_YEAR = 99;
    public static final int MAX_MONTH = 12;
    public static final int DIVISION_BY_400 = 400;
    public static final int FEBRUARY_LEAP_YEAR = 29;
    public static final int FEBRUARY_NOT_LEAP_YEAR = 28;
    public static final int THIRTY_DAYS = 30;
    public static final int THIRTY_ONE_DAYS = 31;
    public static final int RESIDUE = 11;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    private final String idCodeValue;
    enum Gender {
        MALE, FEMALE
    }

    /**
     * Method returns the id cod.
     *
     * @return id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }

    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
        if (!isCorrect() || !idCodeValue.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        if (idCodeValue.length() == ID_CODE_LENGTH) {
            return isGenderNumberCorrect() && isYearNumberCorrect() && isMonthNumberCorrect()
                    && isDayNumberCorrect() && isControlNumberCorrect();
        }
        return false;
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        Gender gender = getGender();
        String date = idCodeValue.substring(5, 7) + "." + idCodeValue.substring(3, 5) + "." + getFullYear();
        String city = getBirthPlace();
        return "This is a " + gender + " born on " + date + " in " + city;
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        if (Character.getNumericValue(idCodeValue.charAt(0)) % 2 == 0) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        int cityCode = Integer.parseInt(idCodeValue.substring(7, 10));
        if (cityCode >= 1 && cityCode <= 10) {
            return "Kuressaarea";
        } else if (cityCode >= TARTU_FROM_1 && cityCode <= TARTU_TO_1
                || cityCode >= TARTU_FROM_2 && cityCode <= TARTU_TO_2) {
            return "Tartua";
        } else if (cityCode >= TALLINN_FROM_1 && cityCode <= TALLINN_TO_1
                || cityCode >= TALLINN_FROM_2 && cityCode <= TALLINN_TO_2) {
            return "Tallinn";
        } else if (cityCode >= KOHTLA_FROM && cityCode <= KOHTLA_TO) {
            return "Kohtla-Järve";
        } else if (cityCode >= NARVA_FROM && cityCode <= NARVA_TO) {
            return "Narva";
        } else if (cityCode >= PARNU_FROM && cityCode <= PARNU_TO) {
            return "Pärnu";
        } else if (cityCode >= PAIDE_FROM && cityCode <= PAIDE_TO) {
            return "Paide";
        } else if (cityCode >= RAKVERE_FROM && cityCode <= RAKVERE_TO) {
            return "Rakvere";
        } else if (cityCode >= VALGA_FROM && cityCode <= VALGA_TO) {
            return "Valga";
        } else if (cityCode >= VILJANDI_FROM && cityCode <= VILJANDI_TO) {
            return "Viljandi";
        } else if (cityCode >= VORU_FROM && cityCode <= VORU_TO) {
            return "Võru";
        }
        return "unknown";
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        if (idCodeValue.charAt(0) == '1' || idCodeValue.charAt(0) == '2') {
            return Integer.parseInt("18" + idCodeValue.substring(1, 3));
        } else if (idCodeValue.charAt(0) == '3' || idCodeValue.charAt(0) == '4') {
            return Integer.parseInt("19" + idCodeValue.substring(1, 3));
        }
        return Integer.parseInt("20" + idCodeValue.substring(1, 3));
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        int genderNum = Character.getNumericValue(idCodeValue.charAt(0));
        return genderNum >= 1 && genderNum <= 6;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        int yearNumber = Integer.parseInt(idCodeValue.substring(1, 3));
        return yearNumber >= 0 && yearNumber <= MAX_YEAR;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        return monthNumber >= 1 && monthNumber <= MAX_MONTH;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        int dayNumber = Integer.parseInt(idCodeValue.substring(5, 7));
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        List<Integer> thirtyOne = List.of(1, 3, 5, 7, 8, 10, DECEMBER);
        List<Integer> thirty = List.of(4, 6, 9, NOVEMBER);
        if (thirtyOne.contains(monthNumber)) {
            return dayNumber >= 1 && dayNumber <= THIRTY_ONE_DAYS;
        } else if (thirty.contains(monthNumber)) {
            return dayNumber >= 1 && dayNumber <= THIRTY_DAYS;
        } else if (isLeapYear(getFullYear())) {
            return dayNumber >= 1 && dayNumber <= FEBRUARY_LEAP_YEAR;
        }
        return dayNumber >= 1 && dayNumber <= FEBRUARY_NOT_LEAP_YEAR;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        int[] multipliers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        int first = Character.getNumericValue(idCodeValue.charAt(0));
        int second = Character.getNumericValue(idCodeValue.charAt(1));
        int third = Character.getNumericValue(idCodeValue.charAt(2));
        int fourth = Character.getNumericValue(idCodeValue.charAt(3));
        int fifth = Character.getNumericValue(idCodeValue.charAt(4));
        int sixth = Character.getNumericValue(idCodeValue.charAt(5));
        int seventh = Character.getNumericValue(idCodeValue.charAt(6));
        int eighth = Character.getNumericValue(idCodeValue.charAt(7));
        int ninth = Character.getNumericValue(idCodeValue.charAt(8));
        int tenth = Character.getNumericValue(idCodeValue.charAt(9));
        int eleventh = Character.getNumericValue(idCodeValue.charAt(10));
        int summa = first * multipliers[0] + second * multipliers[1] + third * multipliers[2]
                + fourth * multipliers[3] + fifth * multipliers[4] + sixth * multipliers[5]
                + seventh * multipliers[6] + eighth * multipliers[7] + ninth * multipliers[8] + tenth * multipliers[9];
        int controlNumber = summa % RESIDUE;
        if (controlNumber == 10) {
            int[] newMultipliers = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            int newSumma = first * newMultipliers[0] + second * newMultipliers[1] + third * newMultipliers[2]
                    + fourth * newMultipliers[3] + fifth * newMultipliers[4] + sixth * newMultipliers[5]
                    + seventh * newMultipliers[6] + eighth * newMultipliers[7] + ninth * newMultipliers[8]
                    + tenth * newMultipliers[9];
            controlNumber = newSumma % RESIDUE;
        }
        if (controlNumber == 10) {
            controlNumber = 0;
        }
        return controlNumber == eleventh;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        if (fullYear % DIVISION_BY_400 == 0) {
            return true;
        }
        return fullYear % 4 == 0 && fullYear % 100 != 0;
    }

    /**asd
     * Run tests.
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("51602290295");
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
        System.out.println(validMaleIdCode.getFullYear());
        System.out.println(validMaleIdCode.isGenderNumberCorrect());
        System.out.println(validMaleIdCode.isYearNumberCorrect());
        System.out.println(validMaleIdCode.isMonthNumberCorrect());
        System.out.println(validMaleIdCode.isDayNumberCorrect());
        System.out.println(validMaleIdCode.isControlNumberCorrect());
        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

}
