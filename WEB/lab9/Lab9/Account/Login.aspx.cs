using System;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lab11.Account
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        private void checkUser(MySqlConnection conn){
            string username = inputUsername.Text;
            string password = inputPassword.Text;
            //ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('" + username +" " + password + "!')", true);
            try
            {
                MySqlCommand cmd = new MySqlCommand("SELECT * FROM user WHERE username ='" + username + "' AND password ='" + password + "'", conn);
                MySqlDataReader reader = cmd.ExecuteReader();
                if (!reader.HasRows)
                {
                    ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Invalid username or password')", true);
                    return;
                }
                else
                {
                    Server.Transfer("~/Webpages/Recipes.aspx", true);
                }
            }
            catch(Exception ex)
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Login failed!')", true);

            }
            
            
        }

        protected void loginButton_Click(object sender, EventArgs e){
            try{
                string connection = "Server=localhost; Database=lab9; Uid=root; Pwd=root; ";
                MySqlConnection conn = new MySqlConnection(connection);
                conn.Open();
                checkUser(conn);
                //ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('MERE BA!')", true);
            }
            catch (Exception ex){
                Console.WriteLine("err:"+ex);
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Cannot connect to database!Err:" + ex +"')", true);
            }
            
        }
    }
}