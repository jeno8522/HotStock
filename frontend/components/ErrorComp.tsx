import Link from "next/link";

const ErrorComp = () => {
  return (
    <div className="flex flex-col w-full h-screen items-center">
      <div className="loading">
        <span>4</span>
        <span>0</span>
        <span>4</span>
      </div>
      <div className="mt-10 text-xl drop-shadow-lg font-bold text-[#555b6c]">
        잘못된 접근이에요
      </div>
      <Link
        href="/"
        className="text-xl drop-shadow-lg font-bold text-[#555b6c] hover:text-[#0e121e]"
      >
        메인으로
      </Link>
    </div>
  );
};

export default ErrorComp;
