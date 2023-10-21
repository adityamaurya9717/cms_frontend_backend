
interface ShowUserRequest{
  name: string | null,
  email:string | null,
  pageNo:number,
  size:number | 20
}

export default ShowUserRequest;