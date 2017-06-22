using System;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lab11.Account
{
    public partial class Authenticate : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        private void addUser(MySqlConnection conn)
        {
            string username = inputUsername.Text;
            string name = inputName.Text;
            string password = inputPassword.Text;

            try
            {
                MySqlCommand cmd = conn.CreateCommand();
                cmd.CommandText = "INSERT INTO user(username, password, name) VALUES(@usr, @pass, @n)";
                cmd.Parameters.AddWithValue("@usr", username);
                cmd.Parameters.AddWithValue("@pass", password);
                cmd.Parameters.AddWithValue("@n", name);
                
                int result = cmd.ExecuteNonQuery();

                if (result == 0)
                {
                    ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Incorect data!')", true);
                }
                else
                {
                    //ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('User created!')", true);
                    Server.Transfer("Login.aspx", true);
                }

            }
            catch (Exception ex)
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Register failed!')", true);
            }
        }

        private static string GetPassword(string password)
        {
            return password;
        }

        protected void SingUpButton_Click(object sender, EventArgs e)
        {
            try
            {
                string connection = "server=127.0.0.1; database=lab9;user=username; password=password;";
                MySqlConnection conn = new MySqlConnection(connection);
                conn.Open();
                addUser(conn);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Cannot connect to database!')", true);
            }
        }
    }
}