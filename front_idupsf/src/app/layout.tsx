import type { Metadata } from "next";
import "./globals.css";
import { Darker_Grotesque, Roboto } from "next/font/google";
import AuthSessionProvider from "@/components/SessionProvider";
import UsuarioLoader from "@/components/UsuarioLoader";

export const metadata: Metadata = {
	title: "Id-UPSF",
	description: "Sistema de Identificação Única da UPSF",
};

const darkerGrotesque = Darker_Grotesque({
	subsets: ["latin"],
	weight: ["400", "500", "600", "700", "800", "900"],
	variable: "--font-darker-grotesque"
});

const roboto = Roboto({
	subsets: ["latin"],
	weight: ["400", "500", "600", "700", "800", "900"],
	variable: "--font-roboto",
});

export default function RootLayout({children,}: Readonly<{children: React.ReactNode;}>) {

	return (
		
		<html lang="en" >
			
			<body className={`${darkerGrotesque.variable} ${roboto.variable}`}>
				
				<AuthSessionProvider>
					
					<UsuarioLoader />
					
					{children}
				
				</AuthSessionProvider>
			
			</body>
		
		</html>
	);
}
