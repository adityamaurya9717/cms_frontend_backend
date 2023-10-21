import axios from "axios";
class RestTemplate{

    static async post(url:string,payload:any,headers:any){
        try{        
          let res  = await axios.post(url,payload,headers)
          return res;
        }
        catch(err){
          throw err;
        }
    
    }
    static get(url:string,headers:any):any{
        axios.get(url,headers)
         .then(res=>{
            console.log("response:",res)
             return res
         }).catch(err=>{
             throw err;
         })    
     }

}

export default RestTemplate;