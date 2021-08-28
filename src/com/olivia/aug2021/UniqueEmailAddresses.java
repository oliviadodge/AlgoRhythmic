package com.olivia.aug2021;

import java.util.Enumeration;

public class UniqueEmailAddresses {
    public static void main(String[] args) {
        String[] emails = new String[]{
                "test.email+alex@leetcode.com",
                "test.email.leet+alex@code.com"
        };

        String[] emails1 = new String[] {
                "fg.r.u.uzj+o.pw@kziczvh.com",
                "r.cyo.g+d.h+b.ja@tgsg.z.com",
                "fg.r.u.uzj+o.f.d@kziczvh.com",
                "r.cyo.g+ng.r.iq@tgsg.z.com",
                "fg.r.u.uzj+lp.k@kziczvh.com",
                "r.cyo.g+n.h.e+n.g@tgsg.z.com",
                "fg.r.u.uzj+k+p.j@kziczvh.com",
                "fg.r.u.uzj+w.y+b@kziczvh.com",
                "r.cyo.g+x+d.c+f.t@tgsg.z.com",
                "r.cyo.g+x+t.y.l.i@tgsg.z.com",
                "r.cyo.g+brxxi@tgsg.z.com",
                "r.cyo.g+z+dr.k.u@tgsg.z.com",
                "r.cyo.g+d+l.c.n+g@tgsg.z.com",
                "fg.r.u.uzj+vq.o@kziczvh.com",
                "fg.r.u.uzj+uzq@kziczvh.com",
                "fg.r.u.uzj+mvz@kziczvh.com",
                "fg.r.u.uzj+taj@kziczvh.com",
                "fg.r.u.uzj+fek@kziczvh.com"
        };
        UniqueEmailAddresses uniqueEmailAddressCounter = new UniqueEmailAddresses();
        System.out.println("num unique emails: " + uniqueEmailAddressCounter.numUniqueEmails(emails));
    }

    public int numUniqueEmails(String[] emails) {

        int numUniqueEmails = 0;
        boolean[] indicesOfDuplicates = new boolean[emails.length];

        for (int i = 0; i < emails.length; i++) {
            if (!indicesOfDuplicates[i]) {
                String emailToCompare = emails[i];
                for (int j = i + 1; j < emails.length; j++) {
                    if (!indicesOfDuplicates[j]) {
                        if (areEquivalentEmails(emailToCompare,
                                emails[j])) {
                            indicesOfDuplicates[i] = true;
                            indicesOfDuplicates[j] = true;
                        }
                    }
                }
                numUniqueEmails++;
            }
        }
        return numUniqueEmails;
    }

    private boolean areEquivalentEmails(String email1, String email2) {
        return simplifyEmail(email1).equals(simplifyEmail(email2));
    }

    private String simplifyEmail(String email) {
        StringBuilder sBuilder = new StringBuilder();

        boolean isLocalName = true;
        boolean isAfterPlus = false;

        for(int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            if ((c != '.') || !isLocalName) {
                if ((c == '+') && isLocalName) {
                    isAfterPlus = true;
                } else if (c == '@') {
                    isLocalName = false;
                    sBuilder.append(c);
                } else {
                    if (!isAfterPlus || !isLocalName) {
                        sBuilder.append(c);
                    }
                }
            }
        }
        return sBuilder.toString();
    }
}
