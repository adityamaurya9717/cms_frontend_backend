export default class TokenService {

    public static set(key:string,value:string){
        localStorage.setItem(key,value);
    }

    public static get(key:string) {
        const value = localStorage.get(key);
        return value;
    }

    public static remove(key:string){
        localStorage.removeItem(key);
    }


}