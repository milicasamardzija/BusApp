import { BusDeparture } from "./BusDeparture";

export interface DrivingLine {
    name: string;
    dateStart: Date;
    dateEnd: Date;
    daysOfWeek: number[];
    busId: number;
    busDepartures: BusDeparture[];
}