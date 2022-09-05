import { Address } from "./Adress";

export interface Emloyee {
    id: number;
    name: string;
    surname: string;
    telephone: string;
    email: string;
    salary: number;
    type: string;
    address: Address;
}