const ErrorComp = () => {
  return (
    <div className="flex flex-col w-full h-screen items-center">
      <div className="loading">
        <span>4</span>
        <span>0</span>
        <span>4</span>
      </div>
      <div className="m-10 text-xl drop-shadow-lg font-bold text-[#43495c]">
        잘못된 접근이에요
      </div>
    </div>
  );
};

export default ErrorComp;
