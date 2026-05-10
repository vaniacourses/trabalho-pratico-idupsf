"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import styles from "./styles.module.css";
import { CarrosselProps } from "@/types/carrosselTypes";



export default function Carrossel({ slides, intervalo = 4000 }: CarrosselProps) {
    const [atual, setAtual] = useState(0);

    useEffect(() => {
        const timer = setTimeout(() => {
        setAtual((prev) => (prev + 1) % slides.length);
        }, intervalo);
        console.log("Timer set");

        return () => clearTimeout(timer); // limpa o timer ao desmontar
    });

    return (
        <div className={styles.carrossel}>
            
            {slides.map((slide, index) => (
                <figure
                key={index}
                className={`${styles.slide} ${index === atual ? styles.slideAtivo : ""}`}
                >
                    <Image
                        src={slide.src}
                        alt={slide.alt}
                        className={styles.imagem}
                        loading="eager" 
                    />
                </figure>
            ))}

            <div className={styles.indicadores}>
                {slides.map((_, index) => (
                <span
                    key={index}
                    className={`${styles.indicador} ${index === atual ? styles.indicadorAtivo : ""}`}
                />
                ))}
            </div>
        
        </div>
    );
}