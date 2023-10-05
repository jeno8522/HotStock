import Image from "next/image";
import darksky from "@/public/images/darksky.jpg";

const Loading = () => {
  return (
    <div className="relative">
      <Image
        src={darksky}
        alt="dark-background"
        layout="fill"
        objectFit="cover"
        className="opacity-80"
      />
      <div className="flex flex-col w-full h-screen items-center">
        <div className="loading">
          <span>L</span>
          <span>o</span>
          <span>a</span>
          <span>d</span>
          <span>i</span>
          <span>n</span>
          <span>g</span>
          <span>.</span>
          <span>.</span>
          <span>.</span>
        </div>
      </div>
    </div>
  );
};

export default Loading;
