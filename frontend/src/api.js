export async function request(path,options={}){
 const res=await fetch('/api'+path,{...options,headers:{...(options.body instanceof FormData?{}:{'Content-Type':'application/json'}),...options.headers}});
 if(!res.ok){let msg='Request failed';try{const e=await res.json();msg=e.error||msg}catch{}throw new Error(msg)}
 if(res.status===204||res.headers.get('content-length')==='0')return null;
 const text=await res.text();return text?JSON.parse(text):null;
}
export async function upload(file){const form=new FormData();form.append('file',file);return (await request('/uploads',{method:'POST',body:form})).url}
