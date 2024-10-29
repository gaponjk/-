import java.util.*;

public class Contact implements Comparable<Contact>, Iterator<String> {
    private String name;
    private String mobilePhone;
    private String workPhone;
    private String homePhone;
    private String email;
    private String webSite;
    private String address;
    private int index = 0;
    private List<String> fields = new ArrayList<>();

    public String getName() {
        return name;
    }


    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }


    public String getHomePhone() {
        return homePhone;
    }

    public String getEmail() {
        return email;
    }


    public String getWebSite() {
        return webSite;
    }


    public String getAddress() {
        return address;
    }


    public Contact(String name, String mobilePhone) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.fields = Arrays.asList(
                name,
                mobilePhone,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Contact(String name, String mobilePhone, String workPhone, String homePhone, String email, String webSite, String address) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.homePhone = homePhone;
        this.email = email;
        this.webSite = webSite;
        this.address = address;
        this.fields = Arrays.asList(
                name,
                mobilePhone,
                workPhone,
                homePhone,
                email,
                webSite,
                address
        );
    }

    @Override
    public String toString() {
        return String.join(", ",
                name,
                mobilePhone,
                workPhone == null ? "" : workPhone,
                homePhone == null ? "" : homePhone,
                email == null ? "" : email,
                webSite == null ? "" : webSite,
                address == null ? "" : address
        );
    }

    public static Contact fromString(String line) {
        String[] areas = line.split(",");
        if (areas.length == 9)
            areas[areas.length - 3] += "," + areas[areas.length - 2] + "," + areas[areas.length - 1];
        else if (areas.length == 8)
            areas[areas.length - 2] += "," + areas[areas.length - 1];
        return new Contact(
                areas[0].trim(),
                areas[1].trim(),
                areas.length > 2 ? areas[2].trim() : null,
                areas.length > 3 ? areas[3].trim() : null,
                areas.length > 4 ? areas[4].trim() : null,
                areas.length > 5 ? areas[5].trim() : null,
                areas.length > 6 ? areas[6].trim() : null
        );
    }

    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.getName());
    }


    @Override
    public boolean hasNext() {
        return index < fields.size();
    }

    public void reset() {
        index = 0;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return fields.get(index++);
    }


}
