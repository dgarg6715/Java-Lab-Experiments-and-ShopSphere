# ShopSphere (Tomcat 11 starter)

## Requirements
- JDK 17 or later
- Maven
- MySQL 8+
- Apache Tomcat 11

## 1. Database
Run `database/shopsphere.sql` in MySQL Workbench.

Default DB settings:
- URL: `jdbc:mysql://localhost:3306/shopsphere?useSSL=false&serverTimezone=UTC`
- User: `root`
- Password: empty

Set `SHOPSPHERE_DB_URL`, `SHOPSPHERE_DB_USER`, and `SHOPSPHERE_DB_PASSWORD` environment variables if your credentials differ. Never commit real passwords.

## 2. Build
From the folder containing `pom.xml`:
```bash
mvn clean package
```
Expected output: `target/ShopSphere.war`

## 3. Deploy to Tomcat
Stop Tomcat. Back up and remove the old `webapps/ShopSphere` directory and old `webapps/ShopSphere.war`. Copy the newly built WAR to `TOMCAT_HOME/webapps/ShopSphere.war`, start Tomcat, and wait for deployment.

Open:
- `http://localhost:8080/ShopSphere/`
- `http://localhost:8080/ShopSphere/products`
- `http://localhost:8080/ShopSphere/register`

## Features in this starter
- Home page and responsive product catalog
- Product detail page
- Registration/login with BCrypt password hashes
- Session-based login/logout
- JDBC DAO with PreparedStatement
- Jakarta Servlet 6.1 / JSP / JSTL for Tomcat 11

## Notes
This is a runnable core starter, not the entire RTU syllabus deliverable. Cart persistence, checkout, order management, admin CRUD, Swing, Socket, RMI, JNDI, internationalization and the related diagrams/tests are not implemented in this starter yet. Add those modules after the core app works.

## Extra starter features
- Session cart (add, update, remove)
- Demo checkout that creates an order and reduces stock transactionally
- Order history
- Authentication and admin filters
- Basic protected admin dashboard at `/admin/dashboard.jsp`

To grant admin access to an existing account, run `UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';` in your local database. Do not expose an admin account publicly.

## RTU demonstration modules
These are educational demo modules and are separate from the database-backed web flow:
- Swing desktop admin demo: run `com.shopsphere.swing.AdminLauncher`
- Socket inventory server: run `com.shopsphere.network.InventoryServer`, then run `com.shopsphere.network.InventoryClient GET_STOCK 1`
- RMI server: run `com.shopsphere.rmi.InventoryRMIServer`, then run `com.shopsphere.rmi.InventoryRMIClient 1`

The Socket and RMI demos use sample in-memory inventory values; they are not yet connected to MySQL. Full admin CRUD, address/coupon/review/wishlist persistence, payment integration, and all advanced JSTL/JNDI syllabus demonstrations would require additional implementation.
