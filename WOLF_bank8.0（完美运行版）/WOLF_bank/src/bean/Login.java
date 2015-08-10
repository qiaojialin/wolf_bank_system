package bean;

public class Login {
		private String userid;
		private String logintime;
		private String loginip;
		public void setUserId(String userId) {
			this.userid = userId;
		}
		public String getUserId() {
			return userid;
		}
		public void setLoginTime(String loginTime) {
			this.logintime = loginTime;
		}
		public String getLoginTime() {
			return logintime;
		}
		public void setLoginIp(String loginIp) {
			this.loginip = loginIp;
		}
		public String getLoginIp() {
			return loginip;
		}
}
