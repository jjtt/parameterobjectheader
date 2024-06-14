with (import <nixpkgs> {});
mkShell {
  buildInputs = [
    temurin-bin-17
    maven
  ];
}
