import { StaticImageData } from "next/image";
import banner1 from "@/assets/banner1.png";
import banner2 from "@/assets/banner2.png";
import banner3 from "@/assets/banner3.png";

export type Slide = {
    src: StaticImageData;
    alt: string;
};

export type CarrosselProps = {
    slides: Slide[];
    intervalo?: number;
};

export const defaultSlides: Slide[] = [
        { src: banner1, alt: "Slide 1" },
        { src: banner2, alt: "Slide 2" },
        { src: banner3, alt: "Slide 3" },
];
