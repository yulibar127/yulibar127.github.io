import profile from '../../src/snirProfile/profile.jpg';

function UserImage({pic})
{
    
    
    return(
        <div className="user-img">
			<img className="dp" src={pic} alt=""/>
		</div>
    );
    
}
export default UserImage;