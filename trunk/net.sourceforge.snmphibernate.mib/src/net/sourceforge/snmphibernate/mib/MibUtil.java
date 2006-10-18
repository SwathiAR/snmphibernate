package net.sourceforge.snmphibernate.mib;

public class MibUtil {

	public static String getOidString(int[] oids) {
		StringBuffer sb = new StringBuffer();
		for(int i : oids) {
			sb.append('.').append(i);
		}
		if(sb.length() > 0) sb.deleteCharAt(0);
		return sb.toString();
	}

	public static String getUpTime(long sysUpTime) {
		long seconds = sysUpTime / 100;
		long secs = seconds % 60;
		seconds /= 60;
		long mins = seconds % 60;
		seconds /= 60;
		long hours = seconds % 24;
		long day =  seconds / 24;
		return getUpTimeString(day, hours, mins, secs);
	}

	private static String getUpTimeString(long day, long hours, long mins, long secs) {
		StringBuffer sb = new StringBuffer();
		sb.append(day).append("�� ");
		sb.append(hours).append("Сʱ ");
		sb.append(mins).append("���� ");
		sb.append(secs).append("��");
		return sb.toString();
	}

	public static String getDate(byte[] dateBytes) {
		if(dateBytes.length < 8) {
			return "�����ʽ";
		}
		StringBuilder sb = new StringBuilder();
		int year = (dateBytes[0] << 8) + (0x00ff & dateBytes[1]);
		sb.append(year + "-");
		int month = dateBytes[2];
		sb.append(month + "-");
		int day = dateBytes[3];
		sb.append(day + " ");
		int hour = dateBytes[4];
		sb.append(hour + ":");
		int minute = dateBytes[5];
		sb.append(minute + ":");
		int second = dateBytes[6];
		sb.append(second);
		return sb.toString();
	}

	public static String getPhysAddr(byte[] physAddressBytes) {
		if(physAddressBytes == null || physAddressBytes.length == 0) {
			return "�����ʽ";
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : physAddressBytes) {
			String s = Integer.toHexString( 0x00ff & b );
			if(s.length() == 1) {
				sb.append("0");
			}
			sb.append( s + ":");
		}
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
}
