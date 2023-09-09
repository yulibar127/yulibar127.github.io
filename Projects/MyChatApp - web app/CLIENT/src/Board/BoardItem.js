import LoginItem from "../Login/LoginItem";
import RegisterItem from "../Register/RegisterItem";
function BoardItem({item,NavigateRegister,NavigateLogin,handleLogin })
{
    
    if(item == "login")
    {
        return(
            <div id="page-wrap1" className="webStyle">
                <div className="container">
                    <LoginItem NavigateRegister={NavigateRegister} NavigateLogin={NavigateLogin} handleLogin={handleLogin}/>
                </div>
            </div>
        ); 
    }
    else if(item == "register")
    {
        return(
            <div id="page-wrap1" className="webStyle">
                <div className="container">
                    <RegisterItem NavigateRegister={NavigateRegister} NavigateLogin={NavigateLogin} handleLogin={handleLogin}/>
                </div>
            </div>
        );
    }
    
    
}
export default BoardItem;