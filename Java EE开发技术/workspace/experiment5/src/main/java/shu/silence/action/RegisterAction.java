package shu.silence.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import shu.silence.DbService;
import shu.silence.model.Users;

import java.util.Map;

public class RegisterAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String repassword;

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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        DbService dbservice = new DbService();
        if (dbservice.hasSameName(username)) {
            this.addFieldError(username, "用户名已存在");
            return INPUT;
        } else {
            dbservice.addUser(user);
        }
        return SUCCESS;
    }

    @Override
    public void validate() {
        // TODO Auto-generated method stub
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        if (!repassword.equals(password)) {
            this.addFieldError(repassword, "重复密码不一致");
        }
    }
}