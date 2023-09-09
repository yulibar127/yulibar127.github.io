
function ImageBox(props)
{
    const { imageUrl } = props;

    

    return(
        <div className="img-box">
            <img className="img-cover" src={imageUrl} alt=""/>
        </div>
    );
    
}
export default ImageBox;