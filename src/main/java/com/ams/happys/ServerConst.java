package com.ams.happys;
import java.util.HashMap;
import java.util.Map;

public class ServerConst {

	public static final String ROLE_TEACHER = "ROLE_TEACHER";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final Integer[] MONTHS = {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
	};
	public static class Quater {
		public static final Integer[] I = {
				1, 2, 3
		};
		public static final Integer[] II = {
				4, 5, 6
		};
		public static final Integer[] III = {
				7, 8, 9
		};
		public static final Integer[] IV = {
				10, 11, 12
		};
		
		public static Integer[] getQuater(Integer quater) {
			switch(quater) {
			case 1:
				return I;
			case 2:
				return II;
			case 3:
				return III;
			case 4:
				return IV;
				default:
					return null;
			}
		}

		public static Map<Integer, Integer[]> getQuaters() {
			Map<Integer, Integer[]> quaters = new HashMap<>();

			quaters.put(1, I);
			quaters.put(2, II);
			quaters.put(3, III);
			quaters.put(4, IV);

			return quaters;
		}
	}

	public static class Semiannual {
		public static final Integer[] I = {
				1, 2, 3, 4, 5, 6
		};
		public static final Integer[] II = {
				7, 8, 9, 10, 11, 12
		};

		public static enum SemiannualName {
			I(1, "Đầu năm"),
			II(2, "Cuối năm"),
			;

			private Integer index;

			private String value;

			private SemiannualName(Integer index, String value) {
				this.index = index;
				this.value = value;
			}

			public Integer getIndex() {
				return index;
			}

			public String getValue() {
				return value;
			}
		}

		public static Map<SemiannualName, Integer[]> getSemiannuals() {
			Map<SemiannualName, Integer[]> map = new HashMap<>();

			map.put(SemiannualName.I, I);
			map.put(SemiannualName.II, II);

			return map;
		}
	}

	public enum ConceptDataType {
		coded(0), // Concept
		booleans(1), // True-False
		datetimes(2), // Date
		numeric(3), // numeric
		text(4)// text
		;

		private Integer value;

		private ConceptDataType(int value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum GenderType {
		unclear("U"), female("F"), male("M"),
		other("other"); // khác

		private String value;

		private GenderType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public static String getGenderTypeEnumFromString(String gender) {
		switch (gender) {
			case "Nam":
				return GenderType.male.getValue();
			case "Nữ":
				return GenderType.female.getValue();
			case "Không rõ": 
				return GenderType.unclear.getValue();
			default:
				return null;
		}
	}

	public static String getGenderTypeFromString(String value) {
		switch (value) {
			case "F":
				return "Nữ";
			case "M":
				return "Nam";
			case "U":
				return "Không rõ";
			default:
				return null;
		}
	}
}
