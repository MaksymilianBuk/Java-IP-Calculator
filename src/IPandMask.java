import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.regex.Pattern;

public final class IPandMask {

    private String ipString = "";
    private String maskString = "";
    private boolean ipFromArgumentLine = true;

    public String getIpString() {
        return ipString;
    }

    public String getMaskString() {
        return maskString;
    }

    public boolean getIpFromArgumentLine() {
        return ipFromArgumentLine;
    }


    public IPandMask(String testString) {
        //Arguments took from VM arguments
        String[] mainParts = testString.split("/"); //spliting by slash
        String[] ip4S = mainParts[0].split(Pattern.quote(".")); //spliting by dot

        boolean isCorrect = true;
        int[] ip4 = new int[4];
        int mask = Integer.parseInt(mainParts[1]);

        //Validation of giving IP and setting to String
        if (ip4S.length == 4) {
            for (int i = 0; i < 4; i++) {
                ip4[i] = Integer.parseInt(ip4S[i]);
                ip4S[i] = Integer.toString(ip4[i], 2);
                ip4S[i] = Editing.addZeros(ip4S[i], 8);
                ipString += ip4S[i];
                ipString += ".";

                if (ip4[i] >= 0 && ip4[i] <= 255) {
                } else {
                    isCorrect = false;
                }
            }
            //Delete last dot
            ipString = ipString.substring(0, 35);


        } else {
            isCorrect = false;
        }

        //Validating mask and setiing to String
        if (mask >= 0 && mask <= 32) {
            maskString = Editing.changeMaskIntToString(mask);
            maskString = Editing.addDotsToMaskAdress(maskString);

        } else {
            isCorrect = false;
        }


        //Arguments took from host
        if (!isCorrect) {
            ipFromArgumentLine = false;

            System.out.println("You have written wrong IP or Mask. Trying to get your computer IP...");
            //Initializing key values to null/zero
            maskString = "";
            ipString = "";
            mask = 0;

            try {
                InetAddress address = Inet4Address.getLocalHost();
                String hostIP = address.getHostAddress();
                //String hostName = address.getHostName();
                //System.out.println("IP: " + hostIP + "\n" + "Name: " + hostName);
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
                mask = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                ip4S = hostIP.split(Pattern.quote("."));
            } catch (Exception e) {
                System.out.println("Something went wrong with getting IP Adress. Exiting program");
                System.exit(0);
            }
            //Set IP String
            for (int i = 0; i < 4; i++) {
                ip4[i] = Integer.parseInt(ip4S[i]);
                ip4S[i] = Integer.toString(ip4[i], 2);
                ip4S[i] = Editing.addZeros(ip4S[i], 8);
                ipString += ip4S[i];
                ipString += ".";
            }
            //Delete last dot
            ipString = ipString.substring(0, 35);

            //Set Mask String
            maskString = Editing.changeMaskIntToString(mask);
            maskString = Editing.addDotsToMaskAdress(maskString);
        }
    }
}
