package com.olivia.aug2021;

public class LicenseKeyFormatting {
    public static void main(String[] args) {
        LicenseKeyFormatting thing = new LicenseKeyFormatting();
        System.out.println("num unique emails: " + thing.licenseKeyFormatting("5F3Z-2e-9-w", 4));
    }

    public String licenseKeyFormatting(String s, int k) {

        StringBuilder formattedLicenseBuilder = new StringBuilder();

        String dashesRemoved = s.replace("-", "");

        int counter = k;

        for (int i = dashesRemoved.length() - 1; i >= 0; i--) {
            if (counter == 0) {
                formattedLicenseBuilder.append('-');
                i++;
                counter = k;
            } else {
                char c = dashesRemoved.charAt(i);
                formattedLicenseBuilder.append(Character.toUpperCase(c));
                counter--;
            }
        }

        return formattedLicenseBuilder.reverse().toString();
    }
}
