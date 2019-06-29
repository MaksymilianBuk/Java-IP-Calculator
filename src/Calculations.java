import java.net.InetAddress;

public abstract class Calculations {

    public static String webAdress(String ip, String mask) {
        String result = "";
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) == '.') {
                result += '.';
            } else if (ip.charAt(i) == '1' && mask.charAt(i) == '1') {
                result += 1;
            } else if (ip.charAt(i) == '0' && mask.charAt(i) == '1') {
                result += 0;
            } else if (ip.charAt(i) == '1' && mask.charAt(i) == '0') {
                result += '0';
            } else
                result += '0';
        }

        return result;
    }


    public static String broadcastAdress(String ip, String mask) {
        String result = "";
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) == '.') {
                result += '.';
            } else if (ip.charAt(i) == '1' && mask.charAt(i) == '1') {
                result += 1;
            } else if (ip.charAt(i) == '0' && mask.charAt(i) == '1') {
                result += 0;
            } else {
                result += '1';
            }
        }

        return result;
    }

    public static String classOfAdress(String ip) {
        ip = ip.substring(0, 8);
        if (ip.charAt(0) == '0')
            return "A";
        else if (ip.charAt(1) == '0')
            return "B";
        else if (ip.charAt(2) == '0')
            return "C";
        else if (ip.charAt(3) == '0')
            return "D";
        else if (ip.charAt(4) == '0')
            return "E";
        else
            System.err.println("Can't calculate class of adress");
        return "ERROR";
    }

    public static String firstHost(String webAdress) {
        String lastOctString;
        int lastOctDecimal;

        //Get last 8 numbers
        lastOctString = webAdress.substring(webAdress.length() - 8, webAdress.length());
        lastOctDecimal = Integer.parseInt(lastOctString, 2);

        //Add 1 to get first host adress (minimal host)
        lastOctDecimal += 1;
        lastOctString = Integer.toString(lastOctDecimal, 2);
        lastOctString = Editing.addZeros(lastOctString, 8);
        webAdress = webAdress.substring(0, webAdress.length() - 8) + lastOctString;

        return webAdress;
    }

    public static String lastHost(String broadcastAdress) {
        String lastOctString;
        int lastOctDecimal;

        //Get last 8 numbers
        lastOctString = broadcastAdress.substring(broadcastAdress.length() - 8, broadcastAdress.length());
        lastOctDecimal = Integer.parseInt(lastOctString, 2);

        //Substract 1 to get first host adress (minimal host)
        lastOctDecimal -= 1;
        lastOctString = Integer.toString(lastOctDecimal, 2);
        lastOctString = Editing.addZeros(lastOctString, 8);
        broadcastAdress = broadcastAdress.substring(0, broadcastAdress.length() - 8) + lastOctString;

        return broadcastAdress;
    }

    public static int hostCounting(String minHost, String maxHost) {
        int howMany = 0;
        int times = 1;
        int[] minHostOctet = Editing.stringDecimalToArrayOfInts(minHost);
        int[] maxHostOctet = Editing.stringDecimalToArrayOfInts(maxHost);

        if (minHost.equals(maxHost)) {
            return howMany;
        }

        howMany = (maxHostOctet[3] - minHostOctet[3]) * times;
        howMany += 2; //Additional 1x2 for first and last digit

        times = (maxHostOctet[2] - 1) - minHostOctet[2];
        howMany += (times * 256);

        howMany += maxHostOctet[3] + 1; //Additional 1 for first zero counting


        return howMany;
    }

    public static String checkPrivacy(String ip) {
        int[] ipOctet = Editing.stringDecimalToArrayOfInts(ip);

        if (ipOctet[0] == 10)
            return "private";
        else if ((ipOctet[0] == 172) && (ipOctet[1] >= 16) && ((ipOctet[1] < 31) || (ipOctet[1] == 31 && ipOctet[2] == 0 && ipOctet[3] == 0)))
            return "private";
        else if ((ipOctet[0] == 192) && (ipOctet[1] == 168))
            return "private";
        else

            return "public";
    }

    public static String pingRequest(String ip) {
        try {
            InetAddress inet = InetAddress.getByName(ip);
            long start = System.currentTimeMillis();
            if (inet.isReachable(5000)) {
                long elapsedTime = System.currentTimeMillis() - start;
                return ip + " is reachable in " + elapsedTime + "ms";
            } else {
                return ip + " is not reachable";
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            return "ERROR";
        }
    }


}
