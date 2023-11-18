import axios from "axios";
import { endPoint, path } from "../constant/EndPoint";

class BrandService{

    static async getAllBrand(){
        try{        
            let url = endPoint.cms + path.cms.getAllBrands
            let res  = await axios.get(url)
            return res.data;
        }
        catch(err){
          throw err;
        }
    
    }
}
export default BrandService