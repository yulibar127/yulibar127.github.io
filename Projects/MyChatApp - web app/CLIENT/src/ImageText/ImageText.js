
function ImageText(props)
{
    return(
        <div className="img-text">
          <div className="user-img">
          <img className="dp" src={props.photo} alt=""/>
        </div>
          <h4>
            {props.name}
            <br/>
            <span>{props.status}</span>
            </h4>
        </div>
        
    );
    
}
export default ImageText;
