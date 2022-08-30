import { Address } from "./Adress";

export interface UserInfo{
    email: string;
    password: string;
    name: string;
    surname: string;
    telephone: string;
    address: Address;
    image: string;
}