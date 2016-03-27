package ro.sci.bookstore;

	public class Customer {
		private String name;
		private Address address;
		private Cart cart;
		private String email;
		
		public Customer(String name, Address address) {
			this.name = name;
			this. address = address;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public Cart getCart() {
			return cart;
		}

		public void setCart(Cart cart) {
			this.cart = cart;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	
		

}
