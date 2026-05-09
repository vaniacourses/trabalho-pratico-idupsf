import Footer from "@/components/Footer";
import NavBar from "@/components/NavBar";

export default function LayoutComNavbar({ children }: { children: React.ReactNode }) {
  return (
    <>
        <NavBar />
        
        {children}
        
        <Footer />
    </>
  );
}

