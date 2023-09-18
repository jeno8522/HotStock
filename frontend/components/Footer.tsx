import Image from "next/image";
import Link from "next/link";
import logo from "@/public/images/hotstocklogo.png"

const namelist = [{name : "김민우"}, {name : "김현수"}, {name : "나현웅"}, {name : "윤혜민"}, {name : "정지원"}, {name : "주창훈"} ];

export default function Footer() {
  return (
  <footer className='bg-zinc-700 mt-5 px-8 py-12 sm:px-16 text-neutral-50'>
    <div className="flex flex-col">
      <Image src={logo} alt='logo' width={118} height={18} className='object-contain' />
      <div className='flex max-md:flex-col my-5 flex-wrap justify-between gap-5'>
        <div className='flex flex-col justify-start items-start gap-6'>
          <div>
            Hot stock<br />
            All Rights Reserved &copy;
          </div>
        </div>

        <div className="flex gap-3">
          {namelist.map((item) => (
            <div key={item.name} className="gap-5">
              {item.name}
            </div>
          ))}
        </div>
      </div>
      <div className='flex justify-between items-center flex-wrap mt-12'>
        <div>SSAFY 9기 특화프로젝트</div>
        <div>
          냥냥 정책 어쩌고
        </div>
      </div>
    </div>
  </footer>
  );
}
