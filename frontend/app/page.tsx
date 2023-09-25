import GetSphere from "@/components/GetSphere";
import Image from "next/image";
import darksky from "@/public/images/darksky.jpg";

const Home = () => {
  return (
    <div className="relative">
      <Image
        src={darksky}
        alt="dark-background"
        layout="fill"
        objectFit="cover"
      />
      <GetSphere />
    </div>
  );
};

export default Home;
