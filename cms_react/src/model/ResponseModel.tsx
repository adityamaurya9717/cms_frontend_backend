type ResponseModelDataType= {
    statusCode:number|null;
    message:string|null;
    success:string|null;
    data:any;
}
const ResponseModel:ResponseModelDataType={
    statusCode:null,
    message:null,
    success:null,
    data:null,
} 
export {
    ResponseModel
};
export type { ResponseModelDataType };
