package com.training.bean;


	public class RegisterBean {
		private String emailId;
		private String firstName;
		private String lastName;

		public RegisterBean() {
		}

		public RegisterBean(String emailId,String firstName, String lastName) {
			super();
			this.emailId=emailId;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getEmailId() {
			return emailId;
		}
		
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		
		public String getFirstname() {
			return firstName;
		}

		public void setFirstname(String firstName)	 {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastname(String lastName) {
			this.lastName = lastName;;
		}

		@Override
		public String toString() {
			return "RegisterBean [emailId="+ emailId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
		}

	}

