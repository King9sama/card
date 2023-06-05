import java.util.*;

/**
 * @Description
 * @Author jiahao.liu
 * @Data 2023/5/20 10:39
 */
public class ConvertTest {
    public static void main(String[] args) {

        Info cardNo = new Info("cardNo", "8807551010004066199");
        Info outTradeNo = new Info("outTradeNo", "737411100011000100");
        Info amount = new Info("amount", "1000");
        Info nonceStr = new Info("nonce_str", "ibuaivckdpRxkhJA");

        HashMap<String, String> hashMap = new HashMap<>();

        ArrayList<Info> infoList = new ArrayList<>();
        infoList.add(cardNo);
        infoList.add(outTradeNo);
        infoList.add(amount);
        infoList.add(nonceStr);

        for (int i = 0; i < infoList.size(); i++) {
            hashMap.put(infoList.get(i).getKey(), infoList.get(i).getValue());
        }
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        ArrayList<Info> infoList2 = new ArrayList<>();

        while (iterator.hasNext()) {
            infoList2.add(new Info(iterator.next().getKey(), iterator.next().getValue()));
        }
        Collections.sort(infoList2, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < infoList.size(); i++) {
            stringBuilder.append(infoList.get(i).getKey() + "=" + infoList.get(i).getValue());
            if (i < infoList.size() - 1) {
                stringBuilder.append("&");
            }
        }
        System.out.println(stringBuilder);
    }
}

class Info {
    private String key;

    private String value;

    public Info() {
    }

    public Info(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Info{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}


