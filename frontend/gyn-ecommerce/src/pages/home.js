import { useState } from "react";
import { motion } from "framer-motion";

export default function Home() {
  return (
    <div className="bg-gray-900 text-white min-h-screen">
      <header className="flex justify-between items-center p-6 bg-gray-800 shadow-md">
        <h1 className="text-2xl font-bold">Gym Management</h1>
        <nav>
          <ul className="flex space-x-6 align-items-center">
            <li><a href="#about" className="hover:text-yellow-400">Sobre</a></li>
            <li><a href="#plans" className="hover:text-yellow-400">Planos</a></li>
            <li><a href="#contact" className="hover:text-yellow-400">Contato</a></li>
          </ul>
        </nav>
      </header>

      <section className="h-screen w-full bg-cover bg-center flex items-center justify-center"
      style={{ backgroundImage: "url('/gym-bg.jpg')" }}>
        <motion.h2 initial={{ opacity: 0, y: -20 }} animate={{ opacity: 1, y: 0 }} className="text-4xl font-extrabold">
          Lorem ipsum dolor sit amet
        </motion.h2>
      </section>

      <section id="about" className="p-16 text-center">
        <h3 className="text-3xl font-bold mb-6">Lorem ipsum dolor sit amet</h3>
        <p className="max-w-2xl mx-auto text-gray-300">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        </p>
      </section>

      <section id="plans" className="p-16 bg-gray-800 text-center">
        <h3 className="text-3xl font-bold mb-6">Nossos Planos</h3>
        <div className="grid md:grid-cols-3 gap-6 max-w-4xl mx-auto">
          <div className="p-6 bg-gray-700 rounded-lg shadow-lg">
            <h4 className="text-xl font-bold">Plano Básico</h4>
            <p className="mt-2">Benefícios Lorem ipsum.</p>
            <p className="text-yellow-400 text-xl mt-4">R$99,90/mês</p>
          </div>
          <div className="p-6 bg-yellow-200 text-gray-900 rounded-lg shadow-lg">
            <h4 className="text-xl font-bold">Plano Premium</h4>
            <p className="mt-2">Benefícios Lorem ipsum +</p>
            <p className="text-xl mt-4">R$119,90/mês</p>
          </div>
          <div className="p-6 bg-yellow-300 rounded-lg text-gray-900 shadow-lg">
            <h4 className="text-xl font-bold">Plano Black</h4>
            <p className="mt-2">Benefícios Lorem ipsum ++</p>
            <p className="text-black-400 text-xl mt-4">R$149,90/mês</p>
          </div>
        </div>
      </section>

      <footer id="contact" className="p-6 bg-gray-800 text-center">
        <p>📍 Endereço: lorem ipsum</p>
        <p>📞 (99) 9 9999-9999</p>
        <p>📧 email@email.com</p>
      </footer>
    </div>
  );
}
