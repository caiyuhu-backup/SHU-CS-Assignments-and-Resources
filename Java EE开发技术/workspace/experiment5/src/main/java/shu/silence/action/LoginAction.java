package shu.silence.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shu.silence.DbService;
import shu.silence.model.Users;

public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    String username;
    String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        DbService dbservice = new DbService();
        Users user = new Users();
        user = dbservice.hasUser(username, password);
        if(user.getUsername()==null){
            this.addFieldError("username", "用户名或密码不正确");
            return INPUT;
        }
        else{
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();
            session.put("user", user.getUsername());
        }
        return SUCCESS;
    }
}